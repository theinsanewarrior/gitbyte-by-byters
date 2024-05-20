chrome.runtime.onInstalled.addListener(() => {
    console.log("Extension Installed");
  });
  
  chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
    console.log("Message received in background script:", message);
    if (message.action === "getTabUrl") {
      chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
        console.log("Active tab:", tabs[0]);
        sendResponse({ url: tabs[0].url });
      });
      return true; // Keep the messaging channel open for sendResponse
    }
  });
  