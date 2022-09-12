package eu.accesa.teamview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "TEAMS")
@RedisHash("Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
