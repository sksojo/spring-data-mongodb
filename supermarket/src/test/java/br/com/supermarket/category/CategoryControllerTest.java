package br.com.supermarket.category;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.supermarket.BaseMockTest;
import br.com.supermarket.controller.CategoryController;
import br.com.supermarket.dto.Category;
import br.com.supermarket.exception.CategoryBadRequestException;
import br.com.supermarket.service.CategoryService;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest extends BaseMockTest {

	@MockBean
	private CategoryService categoryService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	@InjectMocks
	private CategoryController categoryController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	private static final String JSON_CREATE_REQUEST_VALID = "json/CategoryCreateRequestValid.json";

	private static final String JSON_UPDATE_REQUEST_VALID = "json/CategoryUpdateRequestValid.json";

	private static final String JSON_REQUEST_INVALID = "json/CategoryRequestInvalid.json";

	private static final String JSON_RESPONSE_VALID = "json/CategoryResponseValid.json";

	private static final String JSON_RESPONSE_LIST_VALID = "json/CategoryFindListResponseValid.json";

	private static final String URI = "/system/category";

	private static final String ID = "/5ced2efe49ae7645f483ad8b";

	@Test
	public void createSuccessTest() throws Exception {

		Mockito.when(categoryService.create(Mockito.any(Category.class))).thenReturn(getCategory());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(generatePayload(JSON_CREATE_REQUEST_VALID)).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_VALID))).andReturn();
	}

	@Test
	public void createBadResquest() throws Exception {

		doThrow(CategoryBadRequestException.class).when(categoryService).create(Mockito.any(Category.class));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(generatePayload(JSON_REQUEST_INVALID));

		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(content().json("{\"code\":\"BAD_REQUEST\"}"));
	}

	@Test
	public void updateSuccessTest() throws Exception {

		Mockito.when(categoryService.update(Mockito.anyString(), Mockito.any(Category.class)))
				.thenReturn(getCategory());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI.concat(ID)).accept(MediaType.APPLICATION_JSON)
				.content(generatePayload(JSON_UPDATE_REQUEST_VALID)).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_VALID))).andReturn();
	}

	@Test
	public void removeSuccessTest() throws Exception {

		Mockito.when(categoryService.update(Mockito.anyString(), Mockito.any(Category.class)))
				.thenReturn(getCategory());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI.concat(ID))
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void findAllSuccessTest() throws Exception {

		Mockito.when(categoryService.findAll()).thenReturn(Stream.of(getCategory()).collect(Collectors.toList()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_LIST_VALID))).andReturn();
	}

	@Test
	public void findAllPageableSuccessTest() throws Exception {

		Mockito.when(categoryService.findAllPageable(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Stream.of(getCategory()).collect(Collectors.toList()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/pageable?skip=0&top=10"))
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_LIST_VALID))).andReturn();
	}

	@Test
	public void findByNameSuccessTest() throws Exception {

		Mockito.when(categoryService.findByName(Mockito.anyString())).thenReturn(getCategory());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/name/{name}"), "category name")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_VALID))).andReturn();
	}

	@Test
	public void findByIdSuccessTest() throws Exception {

		Mockito.when(categoryService.findById(Mockito.anyString())).thenReturn(getCategory());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/id/{id}"), "5ced2efe49ae7645f483ad8b")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_VALID))).andReturn();

	}

	@Test
	public void findByNameRegexSuccessTest() throws Exception {

		Mockito.when(categoryService.findByNameRegex(Mockito.anyString()))
				.thenReturn(Stream.of(getCategory()).collect(Collectors.toList()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/regex/{name}"), "name")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(generatePayload(JSON_RESPONSE_LIST_VALID))).andReturn();
	}

}
