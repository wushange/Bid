package com.dmcc.bid.util;

/**
 * Created by wushange on 2016/08/16.
 */
public interface BaseEvents {
    void setObj(Object obj);

    Object getObj();

    enum CommonEvent implements BaseEvents {
        TOTOP;

        private Object object;

        @Override
        public void setObj(Object obj) {

            this.object = obj;
        }

        @Override
        public Object getObj() {
            return object;
        }
    }

}
