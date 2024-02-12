package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false, length = 20)
    private String name;
    private Integer age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();


    protected User() {}

    public User(String name, Integer age) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }
    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void reutrnBook(String bookName) {
        UserLoanHistory userLoanHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                //첫번째를 찾아서
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        userLoanHistory.doReturn();
    }
}
