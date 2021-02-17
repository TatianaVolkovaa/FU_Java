package com.company.task1;

class Worker implements Listener {
    public void onChange(String action, java.lang.StringBuilder stringBuilder) {
        System.out.println("Уведомление об изменениях\n Действие: " + action +
                "\n Результат: "+stringBuilder.toString());
    }
}