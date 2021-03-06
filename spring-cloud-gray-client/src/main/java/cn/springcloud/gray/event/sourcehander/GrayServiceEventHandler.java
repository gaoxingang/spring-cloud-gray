package cn.springcloud.gray.event.sourcehander;

import cn.springcloud.gray.GrayManager;
import cn.springcloud.gray.UpdateableGrayManager;
import cn.springcloud.gray.event.EventType;
import cn.springcloud.gray.event.GrayEventMsg;
import cn.springcloud.gray.event.SourceType;
import cn.springcloud.gray.local.InstanceLocalInfo;
import cn.springcloud.gray.local.InstanceLocalInfoInitiralizer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class GrayServiceEventHandler implements GraySourceEventHandler {

    private static final Logger log = LoggerFactory.getLogger(GrayServiceEventHandler.class);

    private UpdateableGrayManager grayManager;

    private InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer;

    public GrayServiceEventHandler(UpdateableGrayManager grayManager, InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer) {
        this.grayManager = grayManager;
        this.instanceLocalInfoInitiralizer = instanceLocalInfoInitiralizer;
    }

    @Override
    public void handle(GrayEventMsg eventMsg) {
        if (!Objects.equals(eventMsg.getSourceType(), SourceType.GRAY_SERVICE)) {
            return;
        }

        if(Objects.equals(eventMsg.getEventType(), EventType.UPDATE)){
            log.warn("");
            return;
        }

        InstanceLocalInfo instanceLocalInfo = instanceLocalInfoInitiralizer.getInstanceLocalInfo();

        if (instanceLocalInfo == null) {
            if (instanceLocalInfo != null) {
                if (StringUtils.equals(eventMsg.getServiceId(), instanceLocalInfo.getServiceId())){
                    return;
                }
            }
        }

        grayManager.removeGrayService(eventMsg.getServiceId());
    }
}
