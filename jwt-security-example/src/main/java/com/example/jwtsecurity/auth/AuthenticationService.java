package com.example.jwtsecurity.auth;




public interface AuthenticationService<T>{


        /**
         * @className AuthenticationService
         * @auther rlawl
         * @date 2023-11-09
         * @discription
         * @param authenticationAble
         * @return
         */
        T generatePayload(AuthenticationAble authenticationAble);

        void validate();

}
