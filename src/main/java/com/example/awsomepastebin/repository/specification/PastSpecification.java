package com.example.awsomepastebin.repository.specification;

import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import org.springframework.data.jpa.domain.Specification;

public class PastSpecification {
    public static Specification<Past> byTitle(String title){
        return (root, query, criteriaBuilder) -> {
            if (title!=null&&!title.isBlank()){
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("status"), Status.PUBLIC),
                        criteriaBuilder.like(root.get("title"), "%" + title + "%")
                );
            }
            else {
                return null;
            }
        };
    }

    public static Specification<Past> byBody(String body){
        return (root, query, criteriaBuilder) -> {
            if (body!=null&&!body.isBlank()){
                return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("status"), Status.PUBLIC),
                criteriaBuilder.like(root.get("body"), "%" + body + "%")
                );
            }
            else {
                return null;
            }
        };
    }
}
