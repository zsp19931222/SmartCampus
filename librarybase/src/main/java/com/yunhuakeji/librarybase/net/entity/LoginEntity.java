package com.yunhuakeji.librarybase.net.entity;

/**
 * description:
 * author:created by Andy on 2019/6/27 0027 15:28
 * email:zsp872126510@gmail.com
 */
public class LoginEntity extends BaseEntity {
    /**
     * code : 40001
     * message : 成功
     * otherMsg : null
     * content : {"userCode":"100001TZiO332","token":"e32d654ba58846fd4071b10a2e2fd08b","freshToken":"4a5747516e38f11677821027afe5ef7e"}
     */

    private ContentBean content;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * userCode : 100001TZiO332
         * token : e32d654ba58846fd4071b10a2e2fd08b
         * freshToken : 4a5747516e38f11677821027afe5ef7e
         */

        private String userCode;
        private String token;
        private String freshToken;

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getFreshToken() {
            return freshToken;
        }

        public void setFreshToken(String freshToken) {
            this.freshToken = freshToken;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "userCode='" + userCode + '\'' +
                    ", token='" + token + '\'' +
                    ", freshToken='" + freshToken + '\'' +
                    '}';
        }
    }
}
