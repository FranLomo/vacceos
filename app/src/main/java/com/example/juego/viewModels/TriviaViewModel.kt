package com.example.juego.viewModels

import androidx.lifecycle.ViewModel

data class Question(val text: String, val options: Array<String>, val answerIndex: Int, val correctAnswer : Int)

class TriviaViewModel : ViewModel() {

    var totalQuestions : Array<Question> = arrayOf(
        Question("¿Quiénes eran los vacceos?", arrayOf("Un grupo de monjes medievales", "Un pueblo prerromano", "Un grupo de mercaderes romanos", "Un ejército cartaginés"), 0, 1),
        Question("¿En qué región se asentaban principalmente los vacceos?", arrayOf("Galicia", "Cataluña", "Castilla y León", "Andalucía"), 0, 2),
        Question("¿Cuál era la principal actividad económica de los vacceos?", arrayOf("Comercio", "Agricultura", "Minería", "Caza"), 0, 1),
        Question("¿Qué cultivo era más importante para los vacceos?", arrayOf("Arroz", "Maíz", "Cebada", "Trigo"), 3, 3),
        Question("¿Qué animal domesticado era crucial en la economía vaccea?", arrayOf("Perro", "Cabra", "Oveja", "Caballo"), 2, 2),
        Question("¿Cómo era la organización política de los vacceos?", arrayOf("Un reino centralizado", "Una confederación de ciudades-estado", "Un imperio expansivo", "Un sistema de clanes nómadas"), 1, 1),
        Question("¿Qué nombre recibían las ciudades amuralladas vacceas?", arrayOf("Villas", "Castros", "Oppida", "Fortalezas"), 2, 2),
        Question("¿Con qué otros pueblos prerromanos tuvieron contacto los vacceos?", arrayOf("Celtíberos", "Íberos", "Vascones", "Tartesios"), 0, 0),
        Question("¿Qué influencia cultural importante recibieron los vacceos?", arrayOf("Griega", "Fenicia", "Egipcia", "Céltica"), 3, 3),
        Question("¿Qué tipo de religión practicaban los vacceos?", arrayOf("Politeísta", "Monoteísta", "Ateísta", "Dualista"), 0, 0),
        Question("¿Cuál de estos dioses era adorado por los vacceos?", arrayOf("Lug", "Ra", "Odin", "Zeus"), 0, 0),
        Question("¿Cómo se denominaba la asamblea de los vacceos?", arrayOf("Consejo de ancianos", "Junta de guerreros", "Cámara alta", "Mesa del rey"), 0, 0),
        Question("¿Qué papel tenía la mujer en la sociedad vaccea?", arrayOf("Participaba activamente en la agricultura", "Solo cuidaba de la casa", "Lideraba las ceremonias religiosas", "Formaba parte del ejército"), 0, 0),
        Question("¿Cuál era el principal método de defensa de las ciudades vacceas?", arrayOf("Murallas de piedra", "Trincheras de madera", "Fosos y empalizadas", "Tropas montadas"), 0, 0),
        Question("¿Qué ocurrió con los vacceos tras la llegada de los romanos?", arrayOf("Desaparecieron misteriosamente", "Huyeron al norte de la península", "Fueron conquistados e incorporados al Imperio", "Se aliaron con los romanos y prosperaron"), 2, 2),
        Question("¿Qué ciudad es considerada uno de los principales asentamientos vacceos?", arrayOf("Numancia", "Pintia", "Tartessos", "Segóbriga"), 1, 1),
        Question("¿En qué siglo se produjo la conquista romana del territorio vacceo?", arrayOf("Siglo IV a.C.", "Siglo II a.C.", "Siglo I d.C.", "Siglo V d.C."), 1, 1),
        Question("¿Qué materiales usaban los vacceos para construir sus viviendas?", arrayOf("Piedra y ladrillo", "Adobe y madera", "Hojas de palma", "Tierra y cañas"), 1, 1),
        Question("¿Qué tipo de agricultura practicaban los vacceos?", arrayOf("De secano", "Hidroponía", "De regadío", "Terrazas agrícolas"), 0, 0),
        Question("¿Cuál era una característica distintiva de la sociedad vaccea?", arrayOf("Eran extremadamente guerreros", "Eran conocidos por su equidad y colectivismo", "Despreciaban el comercio", "Adoraban a un único dios"), 1, 1),
        Question("¿Qué lengua hablaban los vacceos?", arrayOf("Latín vulgar", "Un dialecto celta", "Iberorromano", "Vasco antiguo"), 1, 1),
        Question("¿Cómo se llamaban las representaciones de arte vacceas?", arrayOf("Ídolos de arcilla", "Estelas funerarias", "Frescos murales", "Mosaicos de mármol"), 1, 1),
        Question("¿Qué posición tenían los guerreros en la sociedad vaccea?", arrayOf("Eran respetados y protegían la comunidad", "Eran despreciados por la población", "Eran esclavos entrenados", "Solo participaban en festividades"), 0, 0),
        Question("¿Qué alimento era parte fundamental de la dieta vaccea?", arrayOf("Queso de cabra", "Pan de trigo", "Pescado seco", "Miel"), 1, 1),
        Question("¿Cuál era la función de los druidas en la sociedad vaccea?", arrayOf("Eran jueces en las disputas", "Realizaban rituales y ceremonias religiosas", "Eran comerciantes", "Eran carpinteros expertos"), 0, 1),
        Question("¿Qué creencia sobre la vida después de la muerte tenían los vacceos?", arrayOf("Creían en la reencarnación", "Creían en un paraíso celestial", "No tenían creencias definidas", "Creían en la vida eterna bajo tierra"), 0, 0),
        Question("¿Qué elemento arquitectónico era común en los asentamientos vacceos?", arrayOf("Teatros al aire libre", "Torres de vigilancia", "Acueductos", "Bastiones de madera"), 0, 1),
        Question("¿Qué instrumento musical era usado por los vacceos?", arrayOf("Tambores de mano", "Liras", "Trompetas de cuerno", "Flautas de caña"), 0, 3),
        Question("¿Qué festividades celebraban los vacceos?", arrayOf("Rituales de invierno", "Fiestas de cosecha", "Carnavales", "Danza de las estrellas"), 0, 1),
        Question("¿Qué práctica militar era destacada entre los vacceos?", arrayOf("El combate cuerpo a cuerpo", "La emboscada en campos abiertos", "La batalla a caballo", "El uso de arcos y flechas"), 0, 2),
        Question("¿Qué valoraban los vacceos en sus líderes?", arrayOf("Riqueza y poder", "Sabiduría y justicia", "Habilidad en el comercio", "Conexiones con otros pueblos"), 0, 1),
        Question("¿Qué hacía famosa a la alfarería vaccea?", arrayOf("Colores brillantes", "Diseños geométricos", "Figuras de animales", "Escenas de batallas"), 0, 1),
        Question("¿Qué estructura social prevalecía en los vacceos?", arrayOf("Familias reales", "Tribus comunitarias", "Castas", "Clanes separados"), 0, 1),
        Question("¿Cómo comerciaban los vacceos con otros pueblos?", arrayOf("Usaban monedas de oro", "Intercambiaban bienes", "Vendían esclavos", "Ofrecían servicios militares"), 0, 1),
        Question("¿Qué prácticas funerarias realizaban los vacceos?", arrayOf("Cremaban los cuerpos", "Enterraban a los muertos con ofrendas", "Dejaban los cuerpos al aire libre", "Practicaban momificación"), 0, 1),
        Question("¿Qué significa el término 'vacceo'?", arrayOf("Guerrero de la estepa", "Originario de la llanura", "Hombre de la montaña", "Pastor del valle"), 0, 1),
        Question("¿Qué función cumplía el ganado en la sociedad vaccea?", arrayOf("Animal sagrado", "Fuente de alimento y tracción", "Moneda de intercambio", "Símbolo de estatus"), 0, 2),
        Question("¿Qué usaban los vacceos para la construcción de sus armas?", arrayOf("Oro y plata", "Hierro y bronce", "Piedra y madera", "Arcilla"), 0, 1),
        Question("¿Cómo describirías la vestimenta de los vacceos?", arrayOf("Ropajes de seda", "Túnicas y mantos de lana", "Pieles de animales salvajes", "Prendas de lino fino"), 0, 3),
        Question("¿Cuál era una de las ciudades más importantes de los vacceos?", arrayOf("Emporion", "Intercatia", "Segeda", "Olissipo"), 0, 1),
        Question("¿Qué implemento usaban los vacceos para arar?", arrayOf("Arado de madera", "Azada de piedra", "Rastra de hierro", "Palas de bronce"), 0, 0),
        Question("¿Qué animales eran criados principalmente por los vacceos?", arrayOf("Bueyes y caballos", "Cabras y ovejas", "Perros y gatos", "Pájaros y conejos"), 0, 1),
        Question("¿Qué producto de alfarería era típico de los vacceos?", arrayOf("Jarras con decoración geométrica", "Vasijas de metal", "Tazas de oro", "Platos pintados con escenas de caza"), 0, 0),
        Question("¿Cuál era un uso común del ganado entre los vacceos?", arrayOf("Comercio", "Ceremonias religiosas", "Guerra", "Simbología artística"), 0, 2),
        Question("¿Qué evidencia arqueológica importante se encontró en la región de los vacceos?", arrayOf("Ruinas de templos", "Restos de cerámica", "Armas de bronce", "Inscripciones en piedra"), 0, 1),
        Question("¿Qué diferenciaba a los vacceos de otros pueblos prerromanos?", arrayOf("Su enfoque comunitario y equidad", "El uso de carros de guerra", "Su dieta rica en pescado", "El culto a un solo dios"), 0, 0),
        Question("¿Qué significado tenía la agricultura para los vacceos?", arrayOf("Base de la economía", "Actividad secundaria", "Ocupación menor", "Principalmente un rito religioso"), 0, 0),
        Question("¿Qué costumbre marcaba las bodas en la sociedad vaccea?", arrayOf("Intercambio de ganado", "Ceremonias nocturnas", "Rituales en el campo", "Festividades comunales"), 0, 3),
        Question("¿Cuál de estos asentamientos era vacceo?", arrayOf("Lancia", "Numancia", "Segóbriga", "Olissipo"), 0, 0)
        )

    var questions : Array<Question>

    init {
        totalQuestions.shuffle()
        questions= totalQuestions.copyOfRange(0,12)
    }
}
