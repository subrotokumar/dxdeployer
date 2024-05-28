export interface AuthenticateRequestBody {
  username: string;
  password: string;
}

export interface RegisterRequestBody {
  username: string;
  email: string;
  password: string;
}

export interface RefreshToken {
  refreshToken: string;
}

export interface UserDetailResponse {
  username: string;
  email: string;
  role: string;
}

export interface LoginResponse {
  accessToken: {
    token: string;
    expiry: string;
  };
  refreshToken: {
    token: string;
    expiry: string;
  };
}

export interface LoginBody {
  username: String;
  password: String;
}

export interface RegisterBody {
  username: String;
  email: String;
  password: String;
}
