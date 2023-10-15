package apap.ti.silogistik2106653741;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import jakarta.transaction.Transactional;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106653741.dto.GudangMapper;
import apap.ti.silogistik2106653741.dto.KaryawanMapper;
import apap.ti.silogistik2106653741.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653741.service.GudangService;
import apap.ti.silogistik2106653741.service.KaryawanService;

@SpringBootApplication
public class Silogistik2106653741Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106653741Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanService karyawanService, KaryawanMapper karyawanMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			for (int i = 0; i < 3; i++) {
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNama(faker.company().name() + " Warehouse");
				gudangDTO.setAlamat_gudang(faker.address().fullAddress());
	
				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				gudang = gudangService.createGudang(gudang);

				var karyawanDTO = new CreateKaryawanRequestDTO();
				// buat dummy data karyawan
				karyawanDTO.setName(faker.name().fullName());
				karyawanDTO.setJenis_kelamin(faker.random().nextInt(0, 1)); // asumsi 0 untuk laki-laki, 1 untuk perempuan
				karyawanDTO.setTanggal_lahir(faker.date().birthday());

				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
				karyawan = karyawanService.createKaryawan(karyawan);
			}
		};
	}
}
