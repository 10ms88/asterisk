
package com.company.asterisk.core.asterisk;

import com.company.asterisk.entity.AsteriskIncomeMessage;
import com.company.asterisk.entity.AsteriskIncomeMessageType;
import com.company.asterisk.utils.ExceptionUtils;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class AsteriskIncomeMessageDb {
    @Inject
    private Persistence persistence;
    @Inject
    private Metadata metadata;

    public AsteriskIncomeMessage createMessage(AsteriskIncomeMessageType type, AsteriskChannelDto channelDto, String message) {
        AsteriskIncomeMessage asteriskIncomeMessage = null;
        try (Transaction tx = persistence.createTransaction()) {
            asteriskIncomeMessage = metadata.create(AsteriskIncomeMessage.class);

            asteriskIncomeMessage.setType(type);

            if (channelDto != null) asteriskIncomeMessage.setChannelId(channelDto.getId());
            asteriskIncomeMessage.setIncomeJson(message);

            persistence.getEntityManager().persist(asteriskIncomeMessage);
            tx.commit();
        } catch (Exception ex) {
            ExceptionUtils.logErrors(ex);
        }
        return asteriskIncomeMessage;
    }


}
