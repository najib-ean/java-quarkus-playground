package najib.io.modules.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import najib.io.common.BaseMapper;
import najib.io.common.BaseRepository;
import najib.io.common.BaseResource;
import najib.io.common.BaseService;
import najib.io.entities.UserEntity;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;
import najib.io.repositories.UserRepository;

@Path("/user")
public class UserResource extends BaseResource<UserEntity, UserReqDto, UserResDto> {

    @Inject
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @Override
    protected BaseRepository<UserEntity> repository() {
        return userRepository;
    }

    @Override
    protected BaseService<UserEntity, UserReqDto, UserResDto> service() {
        return userService;
    }

    @Override
    protected BaseMapper<UserEntity, UserReqDto, UserResDto> mapper() {
        return userMapper;
    }

    @Override
    protected String moduleName() {
        return "user";
    }
}
