package listener;

import controller.ApplianceController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class MotionListenerTest {
    @Mock
    private ApplianceController applianceController;

    @InjectMocks
    private MotionListener motionListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldToggleAppliancesOnMotonEvent() {

        motionListener = new MotionListener(applianceController);

        motionListener.onEvent(1,1);

        Mockito.verify(applianceController, Mockito.times(1)).onMotionEvent(Matchers.anyInt(), Matchers.anyInt());
    }
}