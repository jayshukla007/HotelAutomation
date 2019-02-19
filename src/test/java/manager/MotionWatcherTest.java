package manager;

import controller.ApplianceController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class MotionWatcherTest {

    @Mock
    private ApplianceController applianceController;

    @InjectMocks
    private MotionWatcher motionWatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        motionWatcher = new MotionWatcher(applianceController, 1, 1);
    }

    @Test
    public void shouldResetMotionEvent(){
        motionWatcher.run();
        Mockito.verify(applianceController, Mockito.times(1)).resetMotionEvent(Matchers.anyInt(), Matchers.anyInt());
    }

}