package org.example.handlers;

abstract class AbstractHandlers implements Handler{

    protected void handle(String params) {

        switch (params.toLowerCase()) {
            case "get":
                get();
                break;
            case "put":
                put();
                break;
            case "post":
                post();
                break;
            case "delete":
                delete();
                break;
        }
    }

    protected abstract void delete();

    protected abstract void put();

    protected abstract void post();

    protected abstract void get();

    ;
}
