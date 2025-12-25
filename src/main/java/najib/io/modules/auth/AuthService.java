package najib.io.modules.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import najib.io.entities.UserEntity;
import najib.io.modules.auth.dto.LoginResDto;
import najib.io.modules.user.UserMapper;
import najib.io.repositories.UserRepository;
import najib.io.utils.PasswordUtil;

@ApplicationScoped
public class AuthService {
    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    public LoginResDto loginService(String email, String password) {
        // find by email
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("User email not found");
        }

        // match or not for password
        boolean isPasswordMatch = PasswordUtil.verify(password, user.getPassword());
        if (!isPasswordMatch) {
            // todo -- ini seharusnya Not Authenticate Exception
            throw new BadRequestException("Invalid password");
        }

        LoginResDto loginResDto = new LoginResDto();
        loginResDto.setUsername(user.getUsername());
        loginResDto.setEmail(user.getEmail());
        loginResDto.setToken("token coba-coba");

        return loginResDto;
    }
}
