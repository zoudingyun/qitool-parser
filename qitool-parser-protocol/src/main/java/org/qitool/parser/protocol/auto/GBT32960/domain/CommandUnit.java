package org.qitool.parser.protocol.auto.GBT32960.domain;

import org.qitool.parser.protocol.auto.GBT32960.enums.CommandIdentification;
import org.qitool.parser.protocol.auto.GBT32960.enums.ResponseFlag;

/**
 * 命令单元
 *
 * @author zoudingyun
 * 2024/5/14 15:07
 */
public class CommandUnit {

    /**
     * 命令标识
     * */
    private CommandIdentification commandIdentification;

    /**
     * 应答标志
     * */
    private ResponseFlag responseFlag;


    public CommandIdentification getCommandIdentification() {
        return commandIdentification;
    }

    public void setCommandIdentification(CommandIdentification commandIdentification) {
        this.commandIdentification = commandIdentification;
    }

    public ResponseFlag getResponseFlag() {
        return responseFlag;
    }

    public void setResponseFlag(ResponseFlag responseFlag) {
        this.responseFlag = responseFlag;
    }
}
