<h1>Andriod CRUD Application</h1>
<p>This is a simple application made during one of my classes in college to preform basic Create, Read, Update and Delete actions using an API I built with Laravel. This project includes a scrolling list of all the records in the database, a individual record details page, and a edit records page.</p>
<p>Link to API data: https://testapi.jdsoftdev.com/api/products</p>
<h2>How its Made:</h2>
<strong>Tech/Languaged I used: </strong><p>Kotlin, Andriod Studio, Volley, Laravel(API)</p>
<p>The idea behind this project was to learn more in depth knowledge around building andriod applications, while still maintaining usable industry knowledge. I started the project by building the main activity, where it uses a RecyclerView to display all records from the database. Upon clicking a single record, it fetches data for that specific record and populates the TextView componenets on the single record activity. From there, you have the option to delete the record, or edit the record. Upon deleting, it deletes the record from the public online hosted database, and swaps back to the main activity, while updating the RecyclerView with up to date data. from the single record activity, if you pressed the edit button, it switches to edit record activity where you can edit the record within the EditText componenets. Upon saving, the activity switches back to the single record page with the updated data.</p>
<h2>Lessons Learned:</h2>
<p>I learned a few things during this project. I have worked with andriod studio for about a year at the time of this project, but one thing that was specifcally new to this project was how to fetch and manipulate data using Volley. Another thing that stood out to me was the use of CRUD requests such as GET, POST, DELETE, and PUT within an andriod application.</p>
<h2>Demo: </h2>
<p>Here is a demo to showcase the functionality and feel of this project, so that you do not have to clone and view the project locally.</p>
