package com.bussinessIt.api;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.entity.Persona;
import com.bussinessIt.api.repository.ClienteRepository;
import com.bussinessIt.api.repository.CuentaRepository;
import com.bussinessIt.api.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	private ClienteRepository clienteRepository;
	private PersonaRepository personaRepository;
	private CuentaRepository cuentaRepository;

	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);
	}

	public ApiApplication( ClienteRepository clienteRepository, PersonaRepository personaRepository, CuentaRepository cuentaRepository) {

		this.clienteRepository = clienteRepository;
		this.personaRepository=personaRepository;
		this.cuentaRepository=cuentaRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		Persona p = new Persona("Ari","F","1234567890","Cuenca","123456");
		personaRepository.save(p);
		Persona p2 = new Persona("Jose Lema","M","1102030508","Otavalo sn y principal","098254785");
		personaRepository.save(p2);
		Persona p3 = new Persona("Marianela Montalvo","F","0102030405","Amazonas y NNUU","097548965");
		personaRepository.save(p3);
		Persona p4 = new Persona("Juan Osorio","M","0301070809","13 junio y Equinoccial","098874587");
		personaRepository.save(p4);

		Cliente c= new Cliente("12345",true,p);
		Cliente c2= new Cliente("1234",true,p2);
		Cliente c3= new Cliente("5678",true,p3);
		Cliente c4= new Cliente("1245",true,p4);
		clienteRepository.save(c);
		clienteRepository.save(c2);
		clienteRepository.save(c3);
		clienteRepository.save(c4);

		Cuenta cuenta = new Cuenta(1000256398L,"Ahorro",500,true,c);
		Cuenta cuenta1 = new Cuenta(478758L,"Ahorro",2000,true,c2);
		Cuenta cuenta2 = new Cuenta(225487L,"Corriente",100,true,c3);
		Cuenta cuenta3 = new Cuenta(495878L,"Ahorro",0,true,c4);
		Cuenta cuenta4 = new Cuenta(496825L,"Ahorro",0,true,c3);
		cuentaRepository.save(cuenta);
		cuentaRepository.save(cuenta1);
		cuentaRepository.save(cuenta2);
		cuentaRepository.save(cuenta3);
		cuentaRepository.save(cuenta4);



	}
}
