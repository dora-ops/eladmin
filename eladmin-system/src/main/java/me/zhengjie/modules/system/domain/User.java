package me.zhengjie.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author jie
 * @date 2018-11-22
 */
@Entity
@Getter
@Setter
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    private String avatar;

    @NotBlank
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Boolean enabled;

    private String password;

    @ColumnDefault("0")
    @Column(name = "p_base_score",nullable=false,columnDefinition="INT default 0")
    private String pindenBaseScore;

    @ColumnDefault("0")
    @Column(name = "p_prize_score",nullable=false,columnDefinition="INT default 0")
    private String pindenPrizeScore;

    @ColumnDefault("0")
    @Column(name = "p_des_score",nullable=false,columnDefinition="INT default 0")
    private String pindenDesScore;

    @ColumnDefault("0")
    @Column(name = "w_score",nullable=false,columnDefinition="INT default 0")
    private String wenhuaScore;

    @ColumnDefault("0")
    @Column(name = "a_score",nullable=false,columnDefinition="INT default 0")
    private String abilityScore;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    private String address;

    @ColumnDefault("0")
    @Column(name = "total_score",nullable=false,columnDefinition="INT default 0")
    private String totalScore;

    @ColumnDefault("0")
    @Column(name = "person_score",nullable=false,columnDefinition="INT default 0")
    private String personScore;


    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    private String sno;

    private Boolean sex;

    private String major;




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                '}';
    }

    public @interface Update {}
}