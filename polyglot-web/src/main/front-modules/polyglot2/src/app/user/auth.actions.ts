import {createAction, props} from "@ngrx/store";
import {UserLoginResponse} from "./model/user-login-response";

export const login = createAction(
  "[Login Page] User Login",
  props<{user:UserLoginResponse}>()
)

export const logout = createAction(
  "[Top Menu] Logout"
)
