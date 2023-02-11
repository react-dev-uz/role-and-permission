package uz.pdp.roleandpermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.roleandpermission.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
