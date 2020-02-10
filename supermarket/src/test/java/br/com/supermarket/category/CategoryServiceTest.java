package br.com.supermarket.category;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.supermarket.BaseMockTest;
import br.com.supermarket.dto.Category;
import br.com.supermarket.repository.CategoryRepository;
import br.com.supermarket.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest extends BaseMockTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	public void createSuccessTest(){
		
		Category actual = getCategory();
		
		when(categoryRepository.save(Mockito.any(Category.class)) ).thenReturn(getCategory());

		Category expected = categoryService.create(getCategory());
		
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDescription(), actual.getDescription());
	}
	
	
	@Test
	public void createErrorTest() {

		doThrow(DataAccessResourceFailureException.class).when(categoryRepository).save(Mockito.any(Category.class));

		assertThatThrownBy(() -> categoryService.create(getCategory())).isInstanceOf(Exception.class);

	}
	

}
