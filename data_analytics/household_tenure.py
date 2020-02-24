import csv 

with open('Practical/housing_tenure.csv', 'r') as file:
    reader = csv.DictReader(file,delimiter=',')
    for row in reader: 
	geogID = row["GEOG_ID"]
        county = row["County_UD"]
	ownerOccupier = row["Owner Occupier with Mortgage (Households)"]
        print("Total number of owner occupiers in the region of {}, in the county of {} is {}".format(geogID, county, ownerOccupier))
