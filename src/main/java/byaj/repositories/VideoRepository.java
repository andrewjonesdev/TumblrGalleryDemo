package byaj.repositories;

/**
 * Created by student on 6/20/17.
 */

import byaj.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    public List<Video> findAllByVideoUserOrderByVideoDateDesc(int num);
    public List<Video> findAllByOrderByVideoDateDesc();
    public List<Video> findAllByOrderByVideoIDDesc();
    public List<Video> findAllByOrderByVideoDateAsc();
    public List<Video> findAllByOrderByVideoIDAsc();
    public List<Video> findAllByVideoAuthorOrderByVideoDateDesc(String username);
    public Video findByVideoID(int num);
}