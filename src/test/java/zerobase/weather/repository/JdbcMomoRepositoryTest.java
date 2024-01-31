package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMomoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){

        // given
        Memo memo = new Memo(2, "this is new momo2!");

        // when
        jdbcMemoRepository.save(memo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "this is new momo2!");
    }

    @Test
    void findAllMemoTest(){
        // given
        List<Memo> memoList = jdbcMemoRepository.findAll();
        System.out.println(memoList);
        // when
        // then
        assertNotNull(memoList);
    }

}