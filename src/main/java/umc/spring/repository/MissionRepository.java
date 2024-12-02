package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN m.memberMissionList mm " +
            "WHERE mm.member = :member AND mm.status = :status")
    Page<Mission> findMissionsByMemberAndStatus(
            @Param("member") Member member,
            @Param("status") MissionStatus status,
            PageRequest page);

    Page<Mission> findAllbyStore(Store store, PageRequest pageRequest);
}
