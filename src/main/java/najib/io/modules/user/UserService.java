package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import najib.io.common.BaseMapper;
import najib.io.common.BaseRepository;
import najib.io.common.BaseService;
import najib.io.common.BaseValidator;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;

@ApplicationScoped
public class UserService extends BaseService<UserEntity, UserReqDto, UserResDto> {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Inject
    UserValidator userValidator;

    @Override
    protected BaseRepository<UserEntity> repository() {
        return userRepository;
    }

    @Override
    protected BaseMapper<UserEntity, UserReqDto, UserResDto> mapper() {
        return userMapper;
    }

    @Override
    protected String moduleName() {
        return "user";
    }

    @Override
    protected BaseValidator<UserReqDto> validator() {
        return userValidator;
    }
}
