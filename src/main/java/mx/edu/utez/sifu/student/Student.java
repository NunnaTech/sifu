package mx.edu.utez.sifu.student;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name="students")
@Data
public class Student implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
	@Size(max=50)
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;
    
    @NotNull
    @NotBlank
	@Size(min=2, max=50)
    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(50)")
    private String firstName;

	@Size(min=2, max=50)
    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String lastName;

    @NotNull
    @Column(columnDefinition = "timestamp(6)")
    private LocalDateTime birthday;
    
    @NotNull
	@Min(18)
    @Column(columnDefinition = "int(10)")
    private Integer age;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String gender;

    @NotNull
	@Size(min=18)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String curp;

    @NotNull
    @Column(nullable = false)
    private String region;

    @NotNull
    @Column(name="marital_status", nullable = false, columnDefinition = "varchar(50)")
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
    @Column(nullable = false, columnDefinition = "varchar(10)")
    private String zip_code;

    @NotNull
    @NotBlank
    @Email
    @Column(columnDefinition = "varchar(100)")
    private String email;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "varchar(100)")
    private String phone;

    @NotBlank
    @Column(columnDefinition = "double(10,2)")
    private Double salary;

    @Column(name = "is_working", columnDefinition = "boolean default false")
    private Boolean isWorking;

    @Column(name = "is_temporal", columnDefinition = "boolean default false")
    private Boolean isTemporal;

    @NotNull
    @NotBlank
    private String career;

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
