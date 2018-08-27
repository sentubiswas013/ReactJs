import React from 'react';
import { render } from 'react-dom';
import { Wizard, Steps, Step, Navigation } from 'react-albus';
import CenteredDiv from './CenteredDiv';
import { gandalfUrl, dumbledoreUrl, iceKingUrl } from './config.js';

import KeyboardNavigation from './KeyboardNavigation';

const App = () =>
  <CenteredDiv>
    <Wizard>
      <Steps>
        <Step path="gandalf">
          <img src={gandalfUrl} alt="Gandalf" />
        </Step>
        <Step path="dumbledore">
          <img src={dumbledoreUrl} alt="Dumbledore" />
        </Step>
        <Step path="ice king">
          <img src={iceKingUrl} alt="Ice King" />
        </Step>
      </Steps>
      <Navigation>
        <KeyboardNavigation />
      </Navigation>
    </Wizard>
  </CenteredDiv>;

render(<App />, document.getElementById('root'));
