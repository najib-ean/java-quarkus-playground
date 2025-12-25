package najib.io.modules.auth;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import najib.io.modules.auth.dto.LoginReqDto;
import najib.io.modules.auth.dto.LoginResDto;
import najib.io.utils.apiResponse.ApiResponse;

@Path("/auth")
public class AuthResource {
    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public ApiResponse<LoginResDto> login(LoginReqDto payload) {
        LoginResDto loginResDto = authService.loginService(payload.getEmail(), payload.getPassword());
        return ApiResponse.success("success login", loginResDto);
    }

    @POST
    public void logout() {

    }
}
