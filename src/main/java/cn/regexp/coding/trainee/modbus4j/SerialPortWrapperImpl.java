package cn.regexp.coding.trainee.modbus4j;

import com.fazecast.jSerialComm.SerialPort;
import com.serotonin.modbus4j.serial.SerialPortWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Regexpei
 * @date 2024/8/29 23:43
 * @description 窜行端口包装类
 * @since 1.0.0
 */
@Slf4j
public class SerialPortWrapperImpl implements SerialPortWrapper {
    private String commPortId;
    private int baudRate;
    private int flowControlIn;
    private int flowControlOut;
    private int dataBits;
    private int stopBits;
    private int parity;

    private SerialPort serialPort;

    /**
     * @param commPortId     串行端口设备名称
     * @param baudRate       波特率
     * @param flowControlIn  输入流timeout时长 不能超过100
     * @param flowControlOut 输出流timeout时长
     * @param dataBits       数据位
     * @param stopBits       停止位
     * @param parity         校验位
     * @description 串口包装类
     */
    public SerialPortWrapperImpl(String commPortId, int baudRate, int flowControlIn,
                                 int flowControlOut, int dataBits, int stopBits, int parity) {
        super();
        this.commPortId = commPortId;
        this.baudRate = baudRate;
        this.flowControlIn = flowControlIn;
        this.flowControlOut = flowControlOut;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;

        // 当前机器上所有可用的串行端口列表
        SerialPort[] commPorts = SerialPort.getCommPorts();

        log.info("Available Ports:");
        for (SerialPort port : commPorts) {
            log.info(port.getSystemPortName() + ": " + port.getPortDescription());

            if (port.getSystemPortName().equals(this.commPortId)) {
                this.serialPort = port;
                this.serialPort.setComPortParameters(this.baudRate, this.dataBits, this.stopBits, this.parity);
                this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, this.flowControlIn, this.flowControlOut);
                this.serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
                this.serialPort.setComPortParameters(this.baudRate, this.dataBits, this.stopBits, this.parity);
                this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, this.flowControlIn, this.flowControlOut);
                this.serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
                break;
            }
        }
    }

    @Override
    public void close() {
        if (this.serialPort.closePort()) {
            log.info("Port closed successfully.");
        } else {
            log.error("Unable to close the port.");
        }
    }

    @Override
    public void open() {
        if (serialPort.openPort()) {
            log.info("Port opened successfully.");
        } else {
            log.error("Unable to open the port.");
        }
    }

    @Override
    public InputStream getInputStream() {
        InputStream inputStream = null;
        try {
            inputStream = this.serialPort.getInputStream();
        } catch (Exception e) {
            log.error("Get inputStream fail.", e);
        }
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        OutputStream outputStream = null;
        try {
            outputStream = this.serialPort.getOutputStream();
        } catch (Exception e) {
            log.error("Get outputStream fail.", e);
        }
        return outputStream;
    }

    @Override
    public int getBaudRate() {
        return this.baudRate;
    }

    @Override
    public int getDataBits() {
        return this.dataBits;
    }

    @Override
    public int getStopBits() {
        return this.stopBits;
    }

    @Override
    public int getParity() {
        return this.parity;
    }
}
