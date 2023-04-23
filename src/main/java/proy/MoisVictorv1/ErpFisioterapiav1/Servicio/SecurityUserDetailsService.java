package proy.MoisVictorv1.ErpFisioterapiav1.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proy.MoisVictorv1.ErpFisioterapiav1.Model.Empleados;
import proy.MoisVictorv1.ErpFisioterapiav1.Repositorio.EmpleadosRepositorio;


@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private EmpleadosRepositorio empleadosRepositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleados emp1= empleadosRepositorio.findByidentificador(username);
		List<GrantedAuthority> roles= new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails user= new User(emp1.getIdentificador(),emp1.getPassword(),roles);
		return user;
	}

}
