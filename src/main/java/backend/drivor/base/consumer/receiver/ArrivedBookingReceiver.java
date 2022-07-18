package backend.drivor.base.consumer.receiver;

import backend.drivor.base.config.rabbitmq.RabbitMQConfig;
import backend.drivor.base.consumer.event.BookingEvent;
import backend.drivor.base.domain.document.BookingHistory;
import backend.drivor.base.domain.utils.GsonSingleton;
import backend.drivor.base.domain.utils.LoggerUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQConfig.QUEUE_BOOKING + "_ARRIVED_BOOKING", autoStartup = "false")
public class ArrivedBookingReceiver {

    private static final String TAG = ArrivedBookingReceiver.class.getSimpleName();

    @Autowired
    private BookingEvent event;

    @RabbitHandler
    public void arrivedBookingRequest(BookingHistory dataFromQueue) {

        try {
            LoggerUtil.i(TAG, String.format("Booking from queue: {} " + dataFromQueue));
            event.arrivedBookingRequest(dataFromQueue);
            LoggerUtil.i(TAG, String.format("Process data from queue: {}", dataFromQueue));
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e);
        }

    }

}
