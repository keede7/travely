package io.keede.travely.core.external.payment.event;


import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.external.payment.publish.RefundPayment;
import io.keede.travely.core.external.payment.publish.SettlePayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author keede
 * Created on 2024/01/07
 */
@Slf4j
@Component
@Transactional
public class PaymentsEvent {

    @TransactionalEventListener
    public void refund(
            final RefundPayment refundPayment
    ) {
        log.info("Refund Payment : {}", refundPayment);

        Payment payment = refundPayment.payment();

        payment.cancel();

        this.refund(payment);
    }

    // TODO : 같은 트랜잭션에 참여할 것으로 판단
    @TransactionalEventListener
    public void settle(
            final SettlePayment settlePayment
    ) {
        log.info("Settle Payment : {} ", settlePayment);
        /**
         *  ## 예약정보에 현재 예약에 대한 결제 진행상태를 추가한다.
         *  1. 예약정보가 저장된 데이터와 각 정보가 들어있음,
         *  2. 이 정보들을 가지고 결제를 진행시켜서 결과에 따라 다른 과정을 진행시킨다.
         */
        Payment payment = settlePayment.payment();
        // 결제 API 를 호출한다.
        this.pay(payment);
    }

    // TODO : 결제 요청의 응답에 따라서 DB Commit 을 결정하자.
    private void pay(
            final Payment payment
    ) {
        if (payment == null) {
            throw new RuntimeException("결제에 실패했습니다.");
        }

        log.info("결제 API 호출하기");
    }

    private void refund(
            final Payment payment
    ) {
        if(payment == null) {
            throw new RuntimeException("환불에 실패했습니다.");
        }

        log.info("환불 API 호출하기");
    }

}
