package uz.pdp.roleandpermission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.roleandpermission.entity.template.AbstractEntity;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
