package vip.jayson.pojo.dataBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class InterfaceFileBean {
    private LinkedHashMap<String, CodeInfoBean> global;
    private List<CodeBean> receive;
    private List<CodeBean> send;

    public InterfaceFileBean() {
        this.receive = new ArrayList<>();
        this.send = new ArrayList<>();
    }

    public InterfaceFileBean(LinkedHashMap<String, CodeInfoBean> global) {
        this.global = global;
    }

    public InterfaceFileBean(LinkedHashMap<String, CodeInfoBean> global, List<CodeBean> receive, List<CodeBean> send) {
        this.global = global;
        this.receive = receive;
        this.send = send;
    }

    public InterfaceFileBean(LinkedHashMap<String, CodeInfoBean> global, List<CodeBean> receive) {
        this.global = global;
        this.receive = receive;
    }
    public InterfaceFileBean(List<CodeBean> send, LinkedHashMap<String, CodeInfoBean> global) {
        this.global = global;
        this.receive = send;
    }


    public LinkedHashMap<String, CodeInfoBean> getGlobal() {
        return global;
    }

    public void setGlobal(LinkedHashMap<String, CodeInfoBean> global) {
        this.global = global;
    }

    public List<CodeBean> getReceive() {
        return receive;
    }

    public void setReceive(List<CodeBean> receive) {
        this.receive = receive;
    }

    public List<CodeBean> getSend() {
        return send;
    }

    public void setSend(List<CodeBean> send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "InterfaceFileBean{" +
                "global=" + global +
                ", receive=" + receive +
                ", send=" + send +
                '}';
    }
}
