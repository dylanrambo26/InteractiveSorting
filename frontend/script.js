const API_URL = "http://localhost:8080/game"; // Base URL of your backend

let currentGameID = null;//Storing gameID dynamically
// Start a new game
function startGame() {
    const algorithm = document.getElementById("algorithm").value;
    const arraySize = document.getElementById("arraySize").value;
    let index1Element = document.getElementById("index1");
    let index2Element = document.getElementById("index2");
    let actionButtonElement = document.getElementById("performAction");
    switch (algorithm){
        case "selection_sort":
            index1Element.placeholder = "Current Index";
            index1Element.style.width = (index1Element.placeholder.length + 2) +'ch';
            index2Element.placeholder = "Min Index";
            index2Element.style.width = (index2Element.placeholder.length + 2) +'ch';
            break;
        case "insertion_sort":
            index1Element.placeholder = "Sorted Index";
            index1Element.style.width = (index1Element.placeholder.length + 2) +'ch';
            index2Element.placeholder = "Next Element";
            index2Element.style.width = (index2Element.placeholder.length + 2) +'ch';
            actionButtonElement.textContent = "Insert";
            break;
    }

    fetch("http://localhost:8080/game/start", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            algorithm: algorithm,
            arraySize: arraySize
        })
    })
    .then(response => response.json())
    .then(data => {
        currentGameID = data.gameId;
        document.getElementById("gameState").innerText = `Game Started: ${JSON.stringify(data)}`;
        displayArray(data.array);
        //displayPartitions(data.partitionList,data.array);
        console.log("Game started:", data);
    })
    .catch(error => console.error("Error starting game:", error));
}


// Perform action(swap, merge, or insert) on elements in array
function performAction() {
    if(currentGameID === null) {
        alert("Start a game first!");
        return;
    }

    //const currentGameID = 1; //edit later
    const index1 = parseInt(document.getElementById("index1").value);
    const index2 = parseInt(document.getElementById("index2").value);
    const pass = parseInt(document.getElementById("pass").value);

    fetch("http://localhost:8080/game/action", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            gameID: currentGameID,
            pass: pass,
            index1: index1,
            index2: index2
        })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("gameState").innerText = `Game State: ${JSON.stringify(data)}`;
        displayArray(data.array);
    })
    .catch(error => console.error("Error swapping:", error));
}

function displayArray(array) {
    const arrayDiv = document.getElementById("arrayDisplay");
    arrayDiv.innerHTML = ""; // Clear previous content

    array.forEach(num => {
        const span = document.createElement("span");
        span.textContent = "["+ num + "]" + " ";
        span.style.fontSize = "20px";
        span.style.margin = "5px";
        arrayDiv.appendChild(span);
    });
}

function displayPartitions(partitionList, array) {
    const partitionDiv = document.getElementById("partitionDisplay");
    partitionDiv.innerHTML = "";

    if (!partitionList || partitionList.length === 0) {
        partitionDiv.textContent = "No partitions available.";
        return;
    }

    // Root is always at index 0.
    const rootPartition = partitionList[0];
    displayPartitionTree(rootPartition, array, partitionDiv, "");
}

/**
 * Recursively displays a partition node and its children as text with branch connectors.
 * @param {Object} partition - The partition object with properties: left, right, leftChild, rightChild.
 * @param {Array} array - The original array for displaying values.
 * @param {HTMLElement} container - The DOM element to append the output.
 * @param {string} indent - Current indentation for display.
 */
function displayPartitionTree(partition, array, container, indent) {
    // Create a string representing the partition's array slice.
    let partitionText = indent + "[";
    for (let i = partition.left; i <= partition.right; i++) {
        partitionText += array[i] + (i < partition.right ? ", " : "");
    }
    partitionText += "]";

    // Create a pre element so that whitespace and line breaks are preserved.
    const preElem = document.createElement("pre");
    preElem.textContent = partitionText;
    container.appendChild(preElem);

    // If there are children, display a branch line then recursively display children.
    if (partition.leftChild || partition.rightChild) {
        // Simple branch connector line.
        const branchLine = indent + "  /   \\";
        const branchElem = document.createElement("pre");
        branchElem.textContent = branchLine;
        container.appendChild(branchElem);

        // Increase indentation for children.
        if (partition.leftChild) {
            //const newIndent = indent;
            displayPartitionTree(partition.leftChild, array, container, indent);
        }
        if (partition.rightChild) {
            const newIndent = indent + "    ";
            displayPartitionTree(partition.rightChild, array, container, newIndent);
        }
    }
}



