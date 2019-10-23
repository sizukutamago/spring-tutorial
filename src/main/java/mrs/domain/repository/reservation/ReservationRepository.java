package mrs.domain.repository.reservation;

import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);
}
