import React, { useState } from "react";
import ArrayDisplay from "./components/ArrayDisplay";
import GameSetup from "./components/GameSetup";
import ActionPanel from "./components/ActionPanel";
import PartitionTree from "./components/PartitionTree";

import './styles.css';
import './tree.css';

function App() {
    const [gameId, setGameId] = useState(null);
    const [array, setArray] = useState([]);
    const [gameInfo, setGameInfo] = useState({});
    const [message, setMessage] = useState("");
    //const [highlighted, setHighlightedIndices] = useState([]);

  return (
      <div className="min-h-screen bg-gray-50 p-4 text-center">
          <h1 className="text-3xl font-bold mb-6">InteractiveSort</h1>

          <GameSetup
              setGameId={setGameId}
              setArray={setArray}
              setGameInfo={setGameInfo}
          />

          <ArrayDisplay array={array}/>

          <ActionPanel
              gameId={gameId}
              setArray={setArray}
              setMessage={setMessage}
              setGameInfo={setGameInfo}
          />

          {message && (
              <p className="mt-4 font-semibold text-lg">
                  {message}
              </p>
          )}

          {gameInfo.algorithm === "merge_sort" && gameInfo.partitionList && (
              <PartitionTree
                  rootPartition={gameInfo.partitionList[0]}
                  array={array}
              />
          )}
      </div>
  );
}

export default App;
