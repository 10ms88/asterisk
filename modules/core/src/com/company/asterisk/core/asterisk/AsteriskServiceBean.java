package com.company.asterisk.core.asterisk;

import com.company.asterisk.config.AsteriskServerConfig;
import com.company.asterisk.entity.AsteriskIncomeMessageType;
import com.company.asterisk.service.AsteriskService;
import com.company.asterisk.utils.ExceptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service(AsteriskService.NAME)
public class AsteriskServiceBean implements AsteriskService {
    private static final Logger log = LoggerFactory.getLogger(AsteriskServiceBean.class);
    @Inject
    private AsteriskServerConfig config;
    @Inject
    private AsteriskIncomeMessageDb asteriskIncomeMessageDb;

    @Override
    public String initWebSocket() {
        String result;
        try {

            String url = String.format("ws://%s:%s/ari/events?api_key=%s:%s&app=%s",
                    config.getHost(), config.getPort(), config.getUserName(), config.getPassword(), config.getAppName());

            new WebSocketFactory()
                    .createSocket(url)
                    .addListener(new WebSocketAdapter() {
                        @Override
                        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
                            log.info("Connected to Asterisk " + config.getHost());
                        }

                        @Override
                        public void onConnectError(WebSocket websocket, WebSocketException exception) {
                            log.error("Connected to Asterisk error" + exception.getMessage());
                        }

                        @Override
                        public void onTextMessage(WebSocket ws, String message) {
                            Pair<AsteriskIncomeMessageType, AsteriskChannelDto> pair = parseIncomeMessage(message);
                            AsteriskIncomeMessageType messageType = pair.getLeft();
                            AsteriskChannelDto channelDto = pair.getValue();

                            asteriskIncomeMessageDb.createMessage(messageType, channelDto, message);
                        }
                    })
                    .connect();
            result = "Success";
        } catch (Exception e) {
            log.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    private Pair<AsteriskIncomeMessageType, AsteriskChannelDto> parseIncomeMessage(String incomeMessage) {
        Pair<AsteriskIncomeMessageType, AsteriskChannelDto> pair = new MutablePair<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(incomeMessage);
            AsteriskIncomeMessageType type = AsteriskIncomeMessageType.fromId(jsonNode.get("type").asText());

            com.fasterxml.jackson.databind.JsonNode channelJson = jsonNode.get("channel");
            AsteriskChannelDto channelDto = channelJson != null ? AsteriskChannelDto.fromJson(channelJson.toString()) : null;

            pair = new MutablePair<>(type, channelDto);
        } catch (Exception e) {
            ExceptionUtils.logErrors(e);
        }
        return pair;
    }

}
