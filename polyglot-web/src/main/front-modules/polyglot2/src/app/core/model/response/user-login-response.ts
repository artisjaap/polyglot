export interface UserLoginResponse {
  userId:string;
  firstName:string;
  lastName:string;
  username:string;
  password:string;
  token:string;
  roles:string[];
  preferedRole:string;
}
