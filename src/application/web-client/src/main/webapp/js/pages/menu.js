var rootUrl = location.protocol + "//" + window.location.hostname + ":" + location.port;
var rootHbirdwebUrl = rootUrl + "/hbirdweb/";
var halcyonUrl = rootUrl + '/hbird/halcyon/';

var topMenu,
 	tmMenu,	
 	subMenu,
 	closeSubMenuBtn;

var currentIndex = 0;

var menuHome = $('<li><a id=menuHome href=' + rootHbirdwebUrl + 'index.html><span>Home</span></a></li>');
var menuDash = $('<li><a id=menuDash href=' + rootHbirdwebUrl + 'pages/dashboard/hboard.html><span>Dash</span></a></li>');
var menuTm = $('<li><a id=menuTm href=' + rootHbirdwebUrl + 'pages/telemetry/telemetryIndex.html><span>Telemetry</span></a></li>');
var menuCmd = $('<li><a id=menuCmd href=' + rootHbirdwebUrl + 'pages/commanding/commanding.html><span>Commanding</span></a></li>');
var menuHelp = $('<li><a id=menuHelp href=' + rootUrl + 'smc/index.html><span>Help</span></a></li>');

/**
 * On page ready do the following.
 */
jQuery(document).ready(function() {
	setupFrequentUsedDomVars();
	
	setupTitle();

	if(addMenuHtml()) {
		setupMenu();
	}
});

function setupTitle() {
	$.get(halcyonUrl + 'branding/mcsName', null, function(data, textStatus, jqXHR) {
		$('#title').html(jqXHR.responseText);		
	}, 'text');
}

function setupFrequentUsedDomVars() {
	topMenu = $('#topMenu');
	tmMenu = $('#menuTm');
	subMenu = $('#subMenu');
	closeSubMenuBtn = $("#closeButton");
}

/**
 * Adds all the predefined menus to the placeholder (an element with ID = topMenu). If a placeholder is not foudn the function returns false;
 * @returns {Boolean}
 */
function addMenuHtml() {
	if(!topMenu.length) {
		console.log("No menu placeholder");
		return false;
	}
	
	var menuList = $('<ul>');
	
	menuList.append(menuHome);
	menuList.append(menuDash);
	menuList.append(menuTm);
	menuList.append(menuCmd);
	menuList.append(menuHelp);
	
	topMenu.append(menuList);
	
	console.log("Menu contruction complete");
	
	return true;
}

/**
 * Setup menu and submenus.
 */
function setupMenu() {
	// Grab all the menus added in the addMenuHtml function
	var menus = $('a', topMenu);
	
	console.log("Setting up functionality for " + menus.length + " menus.");

	menus.click(function() {
		// Remove styling from all menus. 
		$("#topMenu a").removeClass("activeMenuLink");
		
		// Add active menu style to the clicked menu
		$(this).toggleClass("activeMenuLink");
		
		// If the menu clicked is already active...
		var menuIndex = $(this).parent().index();
		if(currentIndex != menuIndex) {
			// and it's submenu is not open...
			if(!subMenu.hasClass("subMenuOpen")) {
				// open it.
				subMenu.toggleClass("subMenuOpen");
			}
		}
		// else, open the menus submenu
		else {
			subMenu.toggleClass("subMenuOpen");
			if(!subMenu.hasClass("subMenuOpen")) {
				$(this).toggleClass("activeMenuLink");
			}
		}
		currentIndex = menuIndex;
	});
	
	closeSubMenuBtn.click(function() {
		subMenu.removeClass("subMenuOpen");
	});
}
