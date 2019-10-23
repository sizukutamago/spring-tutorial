package mrs.domain.service.reservation;

import mrs.domain.model.*;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservableRoomRepository reservableRoomRepository;


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
        return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
    }

    public Reservation reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
        reservableRoomRepository.findById(reservableRoomId).orElseThrow(() -> new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。"));

        boolean overlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
                .stream()
                .anyMatch(x -> x.overlap(reservation));

        if (overlap) {
            throw new AlreadyReservedException("入力の時間帯はすでに予約済みです");
        }

        String sql = "insert into reservation(end_time, start_time, reserved_date, room_id, user_id) values(:end_time, :start_time, :reserved_date, :room_id, :user_id)";

        LocalDate reservedDate = reservation.getReservableRoom().getReservableRoomId().getReservedDate();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("end_time", LocalDateTime.of(reservedDate.getYear(), reservedDate.getMonth(), reservedDate.getDayOfMonth(), reservation.getEndTime().getHour(), reservation.getEndTime().getMinute()))
                .addValue("start_time", LocalDateTime.of(reservedDate.getYear(), reservedDate.getMonth(), reservedDate.getDayOfMonth(), reservation.getStartTime().getHour(), reservation.getStartTime().getMinute()))
                .addValue("reserved_date", reservation.getReservableRoom().getReservableRoomId().getReservedDate())
                .addValue("room_id", reservation.getReservableRoom().getReservableRoomId().getRoomId())
                .addValue("user_id", reservation.getUser().getUserId());

        jdbcTemplate.update(sql, param);

        return reservation;
    }

    public void cancel(Integer reservationId, User requestUser) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(new Reservation());
        if (RoleName.ADMIN != requestUser.getRoleName() && !Objects.equals(reservation.getUser().getUserId(), requestUser.getUserId())) {
            throw new IllegalStateException("要求されたキャンセルはできません。");
        }
        reservationRepository.delete(reservation);
    }
}
