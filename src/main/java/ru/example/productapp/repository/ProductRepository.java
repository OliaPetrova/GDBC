package ru.example.productapp.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;


	public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getProductName(String name) {
        String sqlQuery = read("fetch_product.sql");
        MapSqlParameterSource params = new MapSqlParameterSource("name", name);
        return jdbcTemplate.queryForObject(sqlQuery, params, String.class);
    }

	private static String read(String scriptFileName) {
		try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
			return bufferedReader.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new RuntimeException("Ошибка при чтении SQL скрипта: " + scriptFileName, e);
		}
	}
}
