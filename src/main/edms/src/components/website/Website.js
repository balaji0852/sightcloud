
import './Website.css';
import app from './main.jpeg';
import stickies from './stickies.jpeg';
import pm from './pm.jpeg';
import newSpace from './newspace.jpeg';
import { React} from 'react';

function Website() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <div className='header-website t1'>Wark.fun</div>
      <div className='embargo'>
        <div className='em1'>
          <h1 className='t1'>#Keep the air thin</h1>
          <h1>Introducing the all new task management app, made with colors</h1>
          <h1>Manage your tasks and team collaboration efficient without hitting the boredom...</h1>
        </div>
        <div className='img'>
          <img className='image' src={app} />
        </div>
      </div>
      <br />
      <br />
      <br />
      <h3 className='t1'>#stickies</h3>
      <div className='embargo'>
        <div className='img'>
          <img className='image' src={stickies} />
        </div>
        <div className='em1'>
          <h1> Organize you're tasks as stickies, And the stickies will keep you posted...</h1>
        </div>
      </div>
      <br />
      <br />
      <br />
      <div className='embargo'>
        <div className='em1'>
          <h1>Create individual space for every new tasks, replace email threads with the new spaces, and make most out of conversation...</h1>
        </div>
        <div className='img'>
          <img className='image' src={newSpace} />
        </div>
      </div>
      <br />
      <br />
      <br />
      <h2 className='t1'>#Project-management</h2>
      <div className='embargo'>
        <div className='img'>
          <img className='image' src={pm} />
        </div>
        <div className='em1'>
          <h1>Project management is made easy, both creation and management...</h1>
        </div>
      </div>
      <br/>
      <br/> <br/>
      <div className='t1 flex'><div className='auto'>wark.fun</div><div className='auto'><a href="mailto:balaji.ui@outlook.com">contact us</a></div></div>
      <br/>
      <br/> <br/>
    </div>
  );
}

export default Website;
