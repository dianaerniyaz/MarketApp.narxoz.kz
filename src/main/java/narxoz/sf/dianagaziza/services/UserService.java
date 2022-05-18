package narxoz.sf.dianagaziza.services;

import narxoz.sf.dianagaziza.model.Users;
import narxoz.sf.dianagaziza.repository.RoleRepository;
import narxoz.sf.dianagaziza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if(user != null){
            return user;
        }
        else throw new UsernameNotFoundException("User Not Found");

    }





}

