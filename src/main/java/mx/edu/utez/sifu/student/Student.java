package mx.edu.utez.sifu.student;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(50)")
    private String firstName;

    @Size(min = 0, max = 50)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(name = "second_name", columnDefinition = "varchar(50)")
    private String secondName;

    @NotBlank
    @NotNull
    private String birthday;

    @NotNull
    @Min(18)
    @Column(columnDefinition = "int(10)")
    private Integer age;

    @NotNull
    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String gender;

    @NotNull
    @NotBlank
    @Size(min = 18, max = 18)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String curp;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String region;

    @NotNull
    @NotBlank
    @Column(name = "marital_status", nullable = false, columnDefinition = "varchar(50)")
    private String maritalStatus;

    @NotNull
    @Column(name = "children", columnDefinition = "boolean default false")
    private Boolean children;

    @NotNull
    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String address;

    @NotNull
    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(70)")
    private String city;

    @NotNull
    @Size(min = 5, max = 5)
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
    @Column(nullable = false, columnDefinition = "varchar(10)")
    private String zip_code;

    @NotNull
    @NotBlank
    @Email
    @Column(columnDefinition = "varchar(100)")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{10}(?:[-\\s]\\d{4})?$")
    @Column(columnDefinition = "varchar(100)")
    private String phone;

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "99999.9")
    @Column(columnDefinition = "double(10,2)")
    private Double salary;

    @Column(name = "is_working", columnDefinition = "boolean default false")
    private Boolean isWorking;

    @Column(name = "is_temporal", columnDefinition = "boolean default false")
    private Boolean isTemporal;

    @NotNull
    @NotBlank
    private String career;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
