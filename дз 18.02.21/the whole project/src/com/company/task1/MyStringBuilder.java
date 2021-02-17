package com.company.task1;

class MyStringBuilder {

    private Listener listener; // слушатель, которого надо уведомить
    private java.lang.StringBuilder stringBuilder; // делегат

    public MyStringBuilder(String string) {
        stringBuilder = new java.lang.StringBuilder(string);
    }

    public MyStringBuilder() {
        stringBuilder = new java.lang.StringBuilder();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void NotifyListener(String action){
        if(listener != null){
            listener.onChange(action, this.stringBuilder);
        }
    }

    public java.lang.StringBuilder append(Object obj) {
        stringBuilder.append(obj);
        NotifyListener("append");
        return this.stringBuilder;
    }

    public java.lang.StringBuilder replace(int start, int end, String str) {
        stringBuilder.replace(start, end, str);
        NotifyListener("replace");
        return this.stringBuilder;
    }

    public java.lang.StringBuilder insert(int index, char[] str, int offset, int len) {
        stringBuilder.insert(index, str, offset, len);
        NotifyListener("insert");
        return this.stringBuilder;
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
