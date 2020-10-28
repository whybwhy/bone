# Spring Boot - CRUD

#### Spring Boot 기반 DB CRUD REST API

- Spring Boot 2.3.4
- Spring Data JPA 2.3.4 (JPA)
- Hibernate 5.4.21 (JPA)
- Lombok (Support)
- JUnit 4 (Test)
- LogBack 1.2.3 (Log)
- MockMvc (Web Test)
- HikariCP 3.4.5 (DB connection pool)
- mysql 8.0

-------
HOST : http://localhost:8080

<pre>
<table>
<tr><td>기능</td><td>httpMethod</td><td>uri</td></tr>
<tr><td>C</td><td>POST</td><td>/v1/board/create</td></tr>
<tr><td>R</td><td>GET</td><td>/v1/board/read</td></tr>
<tr><td>R</td><td>GET</td><td>/v1/board/read/{id}</td></tr>
<tr><td>U</td><td>PUT / POST</td><td>/v1/board/update</td></tr>
<tr><td>D</td><td>DELETE / POST</td><td>/v1/board/delete</td></tr>
</table>
</pre>
 

-------
DDL

use bone
CREATE TABLE `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contents` varchar(100) NOT NULL,
  `create_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) NOT NULL,
  `title` varchar(10) NOT NULL,
  `update_timestamp` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-------

#### Controller 샘플
<pre>
BoardController.java

    @GetMapping("/read/{id}")
    public ResponseEntity<Board> read(@PathVariable("id") long id) throws Exception {

        Optional<Board> boardOptional = service.read(id);
        Board board = boardOptional.orElseThrow(NotFoundException::new);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }
</pre>

-------

#### Service 샘플
<pre>

BoardService.java
    
    @Modifying
    @Transactional
    public Board  update(Board request) throws Exception {

        Optional<Board> boardOptional = this.read(request.getId());
        Board board = boardOptional.orElseThrow(NotFoundException::new);

        board.setContents(request.getContents());
        board.setTitle(request.getTitle());

        return boardRepository.save(board);
    }
</pre>

-------

#### Repository 샘플
<pre>

BoardRespository.java

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}

</pre>

-------

#### Test
BoardControllerTest.java 샘플

<pre>
@Test
    @JunitDocument("Board - 게시물 생성")
    void create() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("title", "title");
        info.add("name", "summer");
        info.add("contents", "contents");

        mockMvc.perform(post("/v1/board/create")
                .params(info))
                .andExpect(status().isOk())
                .andDo(print());
    }
</pre>

