<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
	<style>
	body{
		background-color : #222;
	}
		.wheel {
		stroke-dasharray: 160px;
		animation: spin .3s linear infinite;
	}
	
	a:hover{
		text-decoration:none;
		color : #f0f0f0;
	}
	a{
		color:white;
	}
	
	div{
			display:block;
			margin-left:auto;
			margin-right:auto;
			text-align:center;
		}
		
	@keyframes spin {
	  to {
	    stroke-dashoffset: 450;
	  }
	}

	.road {
		stroke-dasharray: 60px;
		animation: dash .25s linear infinite;
	}
	@keyframes dash {
		to {
			stroke-dashoffset: 120;
		}
	}
	</style>
	<meta charset="UTF-8">
	<title>BIFAM</title>
</head>
<body>
<!-- BIKE -->
	<div class="col-lg-4 col-sm-10" style="position:absolute; top:0; bottom:0; left: 0; right: 0; margin:auto; width:500px; height:300px;">
		<svg xmlns:svg="http://www.w3.org/2000/svg" xmlns="http://www.w3.org/2000/svg" width="100%" viewBox="100 -60 600 600" id="svg2" version="1.1">
		  <defs id="defs4"/>
		  <metadata id="metadata7"/>
		  <g id="layer1">
		    <path class="road" d="M702.81 159.72 139.32 471.79" id="path4438" style="fill:none;stroke-width:1.06;stroke:#fff">
		    </path>
		    <path d="M13.94 462.13 686.55 160.72" id="path4197" style="fill:none;stroke-miterlimit:4;stroke-width:2;stroke:#fff"/>
		    <path class="right" d="M724.07 160.06 401.88 471.89" id="path4195" style="fill:none;stroke-miterlimit:4;stroke-width:3;stroke:#fff"/>
		    <path class="road" d="M715.42 160.65 335.39 471.87" id="path4440" style="fill:none;stroke-width:0.99;stroke:#fff">
		    </path>
		    <g class="bike" id="g4554" transform="translate(-29.798954,-160.1069)">
		      <path d="m452.73 478.59 20.33 30.55 33.09-19.05 18.51-97.2c64.16-21.32 67.22-44.08 89.14 32.48l22.97-10.59C714.97 232.87 400.55 398.93 452.73 478.59Z" id="path4456" style="fill-rule:evenodd;fill:#222"/>
		      <path class="wheel" id="path4136" d="m494.39 482.74c-34.64 58.73-39.07-104.91-39.07-104.91 0 0 0.73-105.49 31.2-71.45 0 0 41.25 119.75 7.86 176.36z" style="fill:none;stroke-miterlimit:4;stroke-width:3;stroke:#fff">
		      </path>
		      <path class="wheel" d="m636.72 394.99c-24.48 64.75-36.34-81.88-36.34-81.88 0 0-8.13-84.68 22.34-50.65 0 0 35.66 75.2 13.99 132.52z" id="path4158" style="fill:none;stroke-miterlimit:4;stroke-width:2;stroke:#fff">
		      </path>
		      <path id="path4434" d="m517.11 251.06c0 0-5 7.96-11.04 12.34-6.03 4.37-31.49-20.17-10.67-27.12l5.32-1.78-41.14-11.44c0 0-15.14-2.02-14.2 16.54 0.58 11.46 11.94 18.83 11.94 18.83 0 0 7.67 1.37 16.67-7.92" style="fill:none;stroke-miterlimit:4;stroke-width:3;stroke:#fff"/>
		      <path id="path4432" d="m572.57 186.6c0 0 5.93-6.38 13.22-3 14.6 6.78 0.68 18.79 0.68 18.79 0 0-8.35-0.76-22.27 4.49-13.92 5.25-20.88 4.49-20.88 4.49 0 0-2.78-5.26 13.93-12.77 16.7-7.5 15.32-12.01 15.32-12.01z" style="fill:none;stroke-miterlimit:4;stroke-width:1.52;stroke:#fff"/>
		      <path id="path4430" d="m573.93 225.68-0.68-19.54" style="fill:none;stroke-miterlimit:4;stroke-width:1.52;stroke:#fff"/>
		      <path id="path4428" d="m480.33 284.63-0.28 10.11 1.65 56.14c0 0-4.87 37.96-10.21 49.75" style="fill:none;stroke-miterlimit:4;stroke-width:2;stroke:#fff"/>
		      <path id="path4426" d="m480.38 283.13 0.48 10.1 5.86 55.81c0 0-2 38.26-6.44 50.48" style="fill:none;stroke-miterlimit:4;stroke-width:2;stroke:#fff"/>
		      <path id="path4424" d="M573.24 225.68 479.28 266.19" style="fill:none;stroke-miterlimit:4;stroke-width:3;stroke:#fff"/>
		      <path id="path4422" d="m569.28 380.51-88.97-97.03 0.4-56.75" style="fill:none;stroke-miterlimit:4;stroke-width:3.5;stroke:#fff"/>
		      <path id="path4420" d="m573.89 226.6c0 1.06 46.64 108.46 46.64 108.46l-56.61 42.2 4.18 2.93" style="fill:none;stroke-miterlimit:4;stroke-width:1.52;stroke:#fff"/>
		      <path id="path4187" d="m634.8 335.34-66.94 45.65 5.54-154.92z" style="fill:none;stroke-miterlimit:4;stroke-width:1.52;stroke:#fff"/>
		      <path id="path4458" d="M572.54 203.93 568.57 376.92" style="fill:none;stroke-miterlimit:4;stroke-width:2.5;stroke:#fff"/>
		      <path id="path4537" d="m472.23 398.44 10.79 0" style="fill:none;stroke:#fff"/>
		      <path id="path4552" d="m635.03 335.48-15.29-1.8" style="fill:none;stroke:#fff"/>
		    </g>
		  </g>
		</svg>
		<h1><a href="home.bf" style="text-align:center;">Go BIFAM !</a></h1>
	</div>
</body>
</html>