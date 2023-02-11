package uz.pdp.roleandpermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.roleandpermission.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
