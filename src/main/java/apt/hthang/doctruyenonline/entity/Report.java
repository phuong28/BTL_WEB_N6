package apt.hthang.doctruyenonline.entity;

import apt.hthang.doctruyenonline.utils.ConstantsStatusUtils;
import apt.hthang.doctruyenonline.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Đời Không Như Là Mơ
 */
@Entity
@Table(name = "report", schema = "")
@Data
@NoArgsConstructor
public class Report implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    @JoinColumn(name = "chapterId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Chapter chapter;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "status")
    private Integer status;
    
    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = DateUtils.getCurrentDate();
        }
        if (status == null) {
            status = ConstantsStatusUtils.REPORT_WAIT;
        }
    }
    
}
